package abyss.bindings;

public class MethodBuilder {
    private final String name;
    private StringBuilder parameters;
    private String returnType;

    public MethodBuilder(String name) {
        this.name = name;
        this.parameters = new StringBuilder();
        this.returnType = "V";
    }

    public abyss.bindings.MethodBuilder addParam(String rawParam) {
        parameters.append(rawParam);
        return this;
    }

    public MethodBuilder addParam(Class<?> clazz) {
        appendClassType(clazz, parameters);
        return this;
    }

    private void appendClassType(Class<?> clazz, StringBuilder parameters) {
        if (clazz.isArray() && clazz.isPrimitive()) {
            parameters.append(clazz.getName().replace(" ", "")
                    .replace("class", ""));
        } else if (clazz.isArray()) {
            parameters.append(clazz.getName().replace(".", "/"));
        } else if (clazz.isPrimitive()) {
            switch (clazz.getName()) {
                case "int" -> parameters.append("I");
                case "boolean" -> parameters.append("Z");
                case "long" -> parameters.append("J");
                case "double" -> parameters.append("D");
                case "float" -> parameters.append("F");
                case "short" -> parameters.append("S");
                case "char" -> parameters.append("C");
                case "byte" -> parameters.append("B");
            }
        } else {
            parameters.append(getObjectSignature(clazz));
        }
    }

    public abyss.bindings.MethodBuilder setReturnType(Class<?> clazz) {
        StringBuilder returnType = new StringBuilder();
        appendClassType(clazz, returnType);
        this.returnType = returnType.toString();
        return this;
    }

    public abyss.bindings.MethodBuilder setReturnType(String ret) {
        this.returnType = ret;
        return this;
    }

    public String getName() {
        return name;
    }

    public String build() {
        StringBuilder method = new StringBuilder("()");
        if (!parameters.isEmpty()) {
            method.insert(1, parameters);
        }
        method.append(returnType);
        return method.toString();
    }

    private static String getObjectSignature(Class<?> clazz) {
        return "L" + clazz.getName()
                .replace(".", "/")
                .replace(" ", "")
                .replace("class", "")
                + ";";
    }
}
