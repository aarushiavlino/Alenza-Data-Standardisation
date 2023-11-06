import domain.chejob.entity.output.CheJobOutputKey

class CheJobKey extends Script{
    @Override
    Object run() {
        Object cheJobInput=binding.getProperty("cheJobInputValue") as Object
        transform(cheJobInput)
    }

    static Object transform(Object cheJobInput){
        Map propertiesMap=cheJobInput.getProperties()
        CheJobOutputKey.apply(propertiesMap.getOrDefault("workInstructionKey", "UNKNOWN") as String,
                propertiesMap.getOrDefault("containerTerminalVisitKey", "UNKNOWN") as String)
    }
}
