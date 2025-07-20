public class Main {

    /**
     *Manuela Ventura , 1172338
     * Traductor de Java a JavaScript 
     */
    
    public static void main(String[] args) {
        // Código Java de ejemplo
        String javaCode = """
            public class Ejemplo {
                private int numero;
                private String texto;

                public Ejemplo(int numero, String texto) {
                    this.numero = numero;
                    this.texto = texto;
                }

                public int getNumero() {
                    return numero;
                }

                public void setNumero(int numero) {
                    this.numero = numero;
                }

                public String getTexto() {
                    return texto;
                }

                public void setTexto(String texto) {
                    this.texto = texto;
                }

                public void mostrarInfo() {
                    System.out.println("Número: " + numero + ", Texto: " + texto);
                }

                public static void main(String[] args) {
                    Ejemplo ej = new Ejemplo(42, "Hola mundo");
                    ej.mostrarInfo();

                    for (int i = 0; i < 5; i++) {
                        System.out.println("Iteración: " + i);
                    }
                }
            }
            """;

        System.out.println("\n--- CÓDIGO JAVA ORIGINAL ---");
        System.out.println(javaCode);

        // Traducir a JavaScript
        String jsCode = traducirJavaAJavaScript(javaCode);

        System.out.println("\n--- CÓDIGO JAVASCRIPT TRADUCIDO ---");
        System.out.println(jsCode);
    }

    public static String traducirJavaAJavaScript(String javaCode) {
        String jsCode = javaCode;

        // Remover declaraciones de clase pública y llaves principales
        jsCode = jsCode.replaceAll("public class \\w+\\s*\\{", "// Clase convertida a JavaScript");

        // Convertir declaraciones de variables
        jsCode = jsCode.replaceAll("private (int|String|boolean|double|float) (\\w+);", "this.$2 = null; // $1");

        // Convertir constructores
        jsCode = jsCode.replaceAll("public (\\w+)\\(([^)]*)\\)\\s*\\{", "function $1($2) {");

        // Convertir métodos públicos
        jsCode = jsCode.replaceAll("public (int|String|boolean|double|float|void) (\\w+)\\(([^)]*)\\)\\s*\\{", 
                                  "this.$2 = function($3) {");

        // Convertir métodos estáticos
        jsCode = jsCode.replaceAll("public static (int|String|boolean|double|float|void) (\\w+)\\(([^)]*)\\)\\s*\\{", 
                                  "function $2($3) {");

        // Convertir System.out.println a console.log
        jsCode = jsCode.replaceAll("System\\.out\\.println\\(", "console.log(");

        // Convertir this.variable = variable; (típico de setters)
        jsCode = jsCode.replaceAll("this\\.(\\w+) = (\\w+);", "this.$1 = $2;");

        // Convertir return statements (mantener como están)
        // jsCode permanece igual para return statements

        // Convertir bucles for
        jsCode = jsCode.replaceAll("for \\(int (\\w+) = (\\d+); \\1 < (\\d+); \\1\\+\\+\\)", 
                                  "for (let $1 = $2; $1 < $3; $1++)");

        // Convertir new ClassName() a new ClassName()
        jsCode = jsCode.replaceAll("(\\w+) (\\w+) = new (\\w+)\\(", "let $2 = new $3(");

        // Limpiar modificadores de acceso restantes
        jsCode = jsCode.replaceAll("public |private |protected ", "");

        // Agregar comentarios explicativos
        jsCode = "// Código traducido de Java a JavaScript\n" +
                "// Nota: Esta es una traducción básica, puede requerir ajustes manuales\n\n" +
                jsCode;

        return jsCode;
    }
}