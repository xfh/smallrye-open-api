package io.smallrye.openapi.runtime.io.header;

/**
 * Constants related to Header
 * 
 * @see <a href="https://github.com/OAI/OpenAPI-Specification/blob/master/versions/3.0.3.md#headerObject">headerObject</a>
 * 
 * @author Phillip Kruger (phillip.kruger@redhat.com)
 * @author Eric Wittmann (eric.wittmann@gmail.com)
 */
public interface HeaderConstant {

    public static final String PROP_NAME = "name";
    public static final String PROP_EXAMPLE = "example";
    public static final String PROP_EXAMPLES = "examples";
    public static final String PROP_DESCRIPTION = "description";
    public static final String PROP_CONTENT = "content";
    public static final String PROP_SCHEMA = "schema";
    public static final String PROP_EXPLODE = "explode";
    public static final String PROP_ALLOW_EMPTY_VALUE = "allowEmptyValue";
    public static final String PROP_REQUIRED = "required";
    public static final String PROP_DEPRECATED = "deprecated";
    public static final String PROP_STYLE = "style";

}
