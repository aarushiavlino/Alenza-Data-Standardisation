package domain.chejob.service

import groovy.lang.Binding
import groovy.util.GroovyScriptEngine

object Testing extends App{
  val groovyEngine: GroovyScriptEngine =new GroovyScriptEngine("navisImpl\\src\\main\\resources")
//  val groovyEngine=new GroovyScriptEngine("configs\\navis\\jobOrder\\")
  val bindings= new Binding()
  while (true) {
    Thread.sleep(5000l)
    bindings.setProperty("testObj",SampleClass("aarushi",29))
    val result=groovyEngine.run("Test.groovy",bindings )
    println(result)
  }
}
case class SampleClass(name:String, age:Int)