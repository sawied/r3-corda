package com.github.sawied.corda.iou.schema

import net.corda.core.schemas.MappedSchema
import net.corda.core.schemas.PersistentState
import java.util.*
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Table

/**
 * IOU family
 */
object IOUSchema

object IOUSchemaV1 : MappedSchema(IOUSchema.javaClass, 1, mappedTypes = listOf(IOUSchemaV1.PersistentIOU::class.java)) {

    @Entity
    @Table(name = "iou_states")
    class PersistentIOU(
        @Column(name = "lender")
        var lenderName: String,

        @Column(name = "borrower")
        var borrowerName: String,

        @Column(name = "value")
        var value: Int,

        @Column(name = "linear_id")
        var linearId: UUID
    ) : PersistentState() {
        constructor() : this("", "", 0, UUID.randomUUID())
    }
}