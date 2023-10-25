object Test extends App{

        val classN=ClassLoader.getSystemClassLoader.loadClass("MainAppServer1")
        println(classN.getSimpleName)
        }
