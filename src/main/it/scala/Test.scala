object Test extends App{

        val classN=ClassLoader.getSystemClassLoader.loadClass("MainApp2")
        println(classN.getSimpleName)
        }
