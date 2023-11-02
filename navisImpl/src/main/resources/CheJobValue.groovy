import domain.chejob.entity.output.CheJobOutputValue
import scala.Option

class CheJobValue extends Script{

    @Override
    Object run() {
        transform(binding.getProperty("cheJobInputValue") as Object)
    }

    Object transform(Object cheJobInput){
        Map propertiesMap=cheJobInput.getProperties()
        CheJobOutputValue.apply(propertiesMap.getOrDefault("containerId", "UNKNOWN") as String,
                propertiesMap.getOrDefault("workInstructionKey", "UNKNOWN") as String,
                propertiesMap.getOrDefault("containerTerminalVisitKey", "UNKNOWN") as String,
                propertiesMap.getOrDefault("blockOfWork", "UNKNOWN") as String,
                propertiesMap.getOrDefault("bayOfWork", "UNKNOWN") as String,
                getRow(propertiesMap),
                getTier(propertiesMap),
                transformMoveStage(propertiesMap.getOrDefault("moveStage", "UNKNOWN") as String),
                transformMoveType(propertiesMap.getOrDefault("moveType", "UNKNOWN") as String)
        )
    }

    static String transformMoveStage(String moveStage){
        switch(moveStage){
            case "PREPARE":
                return "PREPARE"
            case "PICK":
                return "PICK"
            case "MOVE":
                return "MOVE"
            case "DROP":
                return "DROP"
            case "COMPLETE":
                return "COMPLETE"
            default:
                return "UNKNOWN"
        }
    }

    static String transformMoveType(String moveType){
        switch(moveType){
            case "YARD":
                return "YARD"
            case "SHIFT":
                return "YARD"
            default:
                return moveType
        }
    }

    static Option<Integer> getRow(Map propertiesMap){
        if(propertiesMap.getOrDefault("rowOfWork", "UNKNOWN") == "UNKNOWN"){
            return Option.empty()
        }else{
            return Option.apply(propertiesMap.getOrDefault("rowOfWork", "UNKNOWN") as Integer)
        }
    }

    static Option<Integer> getTier(Map propertiesMap){
        if(propertiesMap.getOrDefault("tierOfWork", "UNKNOWN") == "UNKNOWN"){
            return Option.empty()
        }else{
            return Option.apply(propertiesMap.getOrDefault("tierOfWork", "UNKNOWN") as Integer)
        }
    }
}
