package com.avlino.tables.container.service

import com.avlino.service.KStreamsTransformer
import com.avlino.tables.container.entity.{ContainerKey, ContainerValue}

trait ContainerTransformer extends KStreamsTransformer[ContainerKey, ContainerValue]