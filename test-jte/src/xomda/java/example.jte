@import com.example.template.TemplateConfig
@import org.xomda.model.Package

@param org.xomda.model.Package pkg
@param com.example.template.TemplateConfig config

<%-- set the output filename --%>
!{ config.setFilename("test.txt"); }

Out: ${config.getFilename()}
Package found: ${pkg.getName()}