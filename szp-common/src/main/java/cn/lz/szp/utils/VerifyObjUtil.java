package cn.lz.szp.utils;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.FatalBeanException;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
/**
 * quincey
 * Date 2020/7/6 14:09
 */


/**
 * 校验对象中属性的值是否为空
 * 验证空 VerifyObjUtil
 *
 * @author Jane
 * @date 2020/7/5
 */
public class VerifyObjUtil {
    /**
     * 整个类所有属性都要校验
     *
     * @param validateObj 需要校验的对象
     * @return
     */
    public static List<String> validateProperty(Object validateObj) {
        return validateProperty(validateObj, (String[]) null);
    }

    /**
     * 整个类除了忽略的属性，其他都需要校验
     *
     * @param validateObj
     * @param ignoreProperties
     * @return
     */
    public static List<String> validateProperty(Object validateObj, String... ignoreProperties) {
        return validationMethod(validateObj, ignoreProperties, false);
    }



    /**
     * 严重类中指定属性是否为空
     *
     * @param validateObj
     * @param mustProperties
     * @return
     */
    public static List<String> validateMustProperty(Object validateObj, String... mustProperties) {

        return validationMethod(validateObj, mustProperties, true);
    }

    /**
     * 判断指定的属性是否为空
     * @param validateObj
     * @param mustProperties
     * @return
     */
    public static boolean isMustNull(Object validateObj, String... mustProperties) {
        return !validateMustProperty(validateObj, mustProperties).isEmpty();
    }
    /**
     * 判断整个类除了忽略的属性，其他都需要是否为空
     * @param validateObj
     * @param ignoreProperties
     * @return
     */
    public static boolean isIgnoreNull(Object validateObj, String... ignoreProperties) {
        return !validateProperty(validateObj, ignoreProperties).isEmpty();
    }

    /**
     * 验证方法
     * @param validateObj
     * @param validateProperties
     * @param isMust
     * @return
     */
    private static List<String> validationMethod(Object validateObj, String[] validateProperties, boolean isMust) {
        PropertyDescriptor[] targetPds = BeanUtils.getPropertyDescriptors(validateObj.getClass());// 获得每个字段的信息（名字，get set 方法）
        List<String> vList = (validateProperties != null ? Arrays.asList(validateProperties) : null);// 判断是否传递了 指定验证属性或指定忽略属性
        List<String> errList = new ArrayList<>();

        for (PropertyDescriptor targetPd : targetPds) {// 通过访问器导出属性
            Method readMethod = targetPd.getReadMethod();
            if (readMethod != null) {
                boolean condition = isMust ? vList != null && vList.contains(targetPd.getName()) : vList == null || !vList.contains(targetPd.getName());
                if (condition) {// 该属性有get方法 并且 该属性是指定属性，进行操作
                    try {
                        if (!Modifier.isPublic(readMethod.getDeclaringClass().getModifiers())) {// 判断get方法是否为public方法，不是的话，暴力解封
                            readMethod.setAccessible(true);
                        }
                        Object value = readMethod.invoke(validateObj);
                        if (value instanceof String) {// 该属性值是String类型
                            if (StringUtils.isEmpty((String) value)) {
                                errList.add(validateObj.getClass().getSimpleName() + "中的" + targetPd.getName() + "不能为空");
                                continue;
                            }
                        }
                        if (value instanceof Float || value instanceof Integer) {
                            if (StringUtils.isEmpty(value.toString())) {
                                errList.add(validateObj.getClass().getSimpleName() + "中的" + targetPd.getName() + "不能为空");
                                continue;
                            }
                        }
                        if (value == null) {// 该属性值为null
                            errList.add(validateObj.getClass().getSimpleName() + "中的" + targetPd.getName() + "不能为空");
                        }
                    } catch (Throwable ex) {
                        throw new FatalBeanException(
                                "Could not copy property '" + targetPd.getName() + "' from source to target", ex);
                    }
                }
            }

        }
        return errList;
    }

}

