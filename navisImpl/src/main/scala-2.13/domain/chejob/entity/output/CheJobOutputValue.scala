package domain.chejob.entity.output

case class CheJobOutputValue(containerId: String,
                              workInstructionKey:String,
                              containerVisitId: String,
                               blockOfWork: String,
                               bayOfWork: String,
                               rowOfWork: Option[Int],
                               tierOfWork: Option[Int],
                              moveStage: String,
                              moveType: String
                             )

case class CheJobOutputKey(
                            workInstructionKey: String,
                           containerVisitId: String
                          )