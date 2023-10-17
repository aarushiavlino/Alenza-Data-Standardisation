package com.avlino.tables.jobOrder.service

import com.avlino.service.KStreamsTransformer
import com.avlino.tables.jobOrder.entity.JobOrderValue

trait JobOrderTransformer extends KStreamsTransformer[JobOrderKey, JobOrderValue]

