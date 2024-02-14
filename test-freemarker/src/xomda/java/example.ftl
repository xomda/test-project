${setConfig("filename", "output.json")}
{
    "name": "${pkg.name}",
    "packageName": "${pkg.packageName}",
    "packageList": [
    <#if pkg.packageList??>
        <#list pkg.packageList as package>
        <#global pkg = package>
        <#include "/example.ftl"><#if package_has_next>,</#if>
        </#list>
    </#if>
    ],

    "entityList": [
    <#if pkg.entityList??>
        <#list pkg.entityList as entity>
        {
            "name": "${entity.name}",
            "attributeList": [
                <#list entity.attributeList as attribute>
                {
                    "name": "${attribute.name}",
                    "type": "${attribute.type}"
                }<#if attribute_has_next>,</#if>
                </#list>
                ]
        }<#if entity_has_next>,</#if>
        </#list>
    </#if>
    ],

    "enumList": [
    <#if pkg.enumList??>
        <#list pkg.enumList as enum>
        {
            "name": "${enum.name}",
            "valueList": [
                <#list enum.valueList as value>
                "${value.name}"<#if value_has_next>, </#if>
                </#list>
            ]
        }<#if enum_has_next>, </#if>
        </#list>
    </#if>
    ]

}