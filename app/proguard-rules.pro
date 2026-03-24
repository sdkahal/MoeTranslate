# 保持数据模型类不被混淆（假设包名如下，请根据实际修改）
-keep class com.moe.moetranslator.CustomLocale { *; }
-keep class com.moe.moetranslator.translate.** { *; }

# 保持所有资源 ID 不变（防止找不到 R.raw.xxx）
-keepclassmembers class **.R$* {
    public static <fields>;
}

# 保持 XML 解析库不被破坏
-keepattributes Signature,EnclosingMethod,InnerClasses
-keep class javax.xml.** { *; }
-keep class org.w3c.dom.** { *; }