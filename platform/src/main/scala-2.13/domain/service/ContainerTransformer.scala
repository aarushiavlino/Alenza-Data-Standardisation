package domain.service

import com.avlino.tables.container.entity.{ContainerKey, ContainerValue}
import com.avlino.service.KStreamsTransformer

trait ContainerTransformer extends KStreamsTransformer[ContainerKey,ContainerValue]