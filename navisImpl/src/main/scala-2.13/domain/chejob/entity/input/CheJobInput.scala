package domain.chejob.entity.input

case class CheJobInput(
                        workInstructionKey:String,
                        moveType:String,
                        moveStage:String,
                        containerId:String,
                        containerTerminalVisitKey:String,
                        fromLocation:String,
                        toLocation:String,
                        fromLocationType:String,
                        toLocationType:String,
                        positionOfWork:String,
                        blockOfWork:String,
                        bayOfWork:String,
                        stackOfWork:String,
                        rowOfWork:Option[Int],
                        tierOfWork:Option[Int]
                      )
