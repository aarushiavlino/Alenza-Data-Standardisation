package domain.joborder.entity.input

case class VesselVisitDetailsInput (tosKey:String,
                                    id:String,
                                    vesselKey:Option[String],
                                    publishedEta:Option[String],
                                    publishedEtd:Option[String],
                                    berthingPlans:List[BerthingPlansInput]
                                   )

case class BerthingPlansInput(berthingKey:String,
                              seq:Int,
                              eta:Option[Long],
                              etd:Option[Long],
                              berthName:Option[String]
                             )