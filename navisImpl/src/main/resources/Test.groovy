import domain.chejob.service.NameClass
import domain.chejob.service.SampleClass

class Test extends Script {
    @Override
    def run() {
        test(binding.getProperty("testObj") as Object)
//        invokeMethod("test",binding.getProperty("testObj"))
//        println "Hello Testing"
    }

    def test(Object testObj) {
//        println "Hello " + testObj.getProperties().get("name") + " you have turned  " + testObj.getProperties().get("age")
        NameClass nameClass= testObj.getProperties().get("nameClass") as NameClass
        SampleClass.apply(NameClass.apply(nameClass.name() + "") as NameClass, testObj.getProperties().get("city") as String)
    }

}
