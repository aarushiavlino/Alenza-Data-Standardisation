package domain.invUnitFcyVisit.entity.input

case class InventoryFaciltyVisitInput(after: AfterVisitValue)

case class AfterVisitValue(
    gkey: Long,
    visit_state: String,
    arrive_pos_loctype: String,
    arrive_pos_locid: Option[String],
    arrive_pos_loc_gkey: Option[Long],
    actual_ib_cv: Option[Long],
    actual_ob_cv: Option[Long]
)
