import domain.chejob.service.SampleClass

class Test extends Script {
    @Override
    def run() {
        test(binding.getProperty("testObj"))
//        invokeMethod("test",binding.getProperty("testObj"))
//        println "Hello Testing"
    }

    def test(Object testObj) {
//        println "Hello " + testObj.getProperties().get("name") + " you have turned  " + testObj.getProperties().get("age")
//        "Hello " + testObj.getProperties().get("name") + " you have turned  " + testObj.getProperties().get("age")
        SampleClass.apply(testObj.getProperties().get("name")+" Maheshwari " as String, testObj.getProperties().get("age") +1 as int)
    }
}
