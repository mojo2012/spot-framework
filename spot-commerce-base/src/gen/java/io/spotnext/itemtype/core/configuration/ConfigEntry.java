/**
 * This file is auto-generated. All changes will be overwritten.
 */
package io.spotnext.itemtype.core.configuration;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;

import io.spotnext.core.infrastructure.annotation.Accessor;
import io.spotnext.core.infrastructure.annotation.ItemType;
import io.spotnext.core.infrastructure.annotation.Property;
import io.spotnext.core.infrastructure.annotation.Relation;

import io.spotnext.itemtype.core.UniqueIdItem;
import io.spotnext.itemtype.core.configuration.Configuration;

import java.io.Serializable;

import java.lang.Double;
import java.lang.Float;
import java.lang.Integer;
import java.lang.Long;
import java.lang.String;


/**
 * This type can be used to store a configuration entry.
 */
@SuppressWarnings("unchecked")
@SuppressFBWarnings({"MF_CLASS_MASKS_FIELD",
    "EI_EXPOSE_REP",
    "EI_EXPOSE_REP2"
})
@ItemType(persistable = true, typeCode = "configentry")
public class ConfigEntry extends UniqueIdItem {
    /** Default serialVersionUID value. */
    private static final long serialVersionUID = 1L;
    public static final String TYPECODE = "configentry";
    public static final String PROPERTY_STRING_VALUE = "stringValue";
    public static final String PROPERTY_INT_VALUE = "intValue";
    public static final String PROPERTY_LONG_VALUE = "longValue";
    public static final String PROPERTY_DOUBLE_VALUE = "doubleValue";
    public static final String PROPERTY_FLOAT_VALUE = "floatValue";
    public static final String PROPERTY_DESCRIPTION = "description";
    public static final String PROPERTY_CONFIGURATION = "configuration";
    @Property(readable = true, writable = true)
    protected String stringValue;
    @Property(readable = true, writable = true)
    protected Integer intValue;
    @Property(readable = true, writable = true)
    protected Long longValue;
    @Property(readable = true, writable = true)
    protected Double doubleValue;
    @Property(readable = true, writable = true)
    protected Float floatValue;

    /**
     * The short description of the configuration entry's purpose.
     */
    @Property(readable = true, writable = true)
    protected String description;

    /**
     * The config entries referenced by this configuration.
     */
    @Property(readable = true, writable = true)
    @Relation(relationName = "Configuration2ConfigEntry", mappedTo = "entries", type = io.spotnext.core.infrastructure.type.RelationType.ManyToOne, nodeType = io.spotnext.core.infrastructure.type.RelationNodeType.TARGET)
    public Configuration configuration;

    @Accessor(propertyName = "doubleValue", type = io.spotnext.core.infrastructure.type.AccessorType.set)
    public void setDoubleValue(Double doubleValue) {
        this.doubleValue = doubleValue;
    }

    @Accessor(propertyName = "longValue", type = io.spotnext.core.infrastructure.type.AccessorType.get)
    public Long getLongValue() {
        return this.longValue;
    }

    @Accessor(propertyName = "longValue", type = io.spotnext.core.infrastructure.type.AccessorType.set)
    public void setLongValue(Long longValue) {
        this.longValue = longValue;
    }

    @Accessor(propertyName = "intValue", type = io.spotnext.core.infrastructure.type.AccessorType.set)
    public void setIntValue(Integer intValue) {
        this.intValue = intValue;
    }

    @Accessor(propertyName = "stringValue", type = io.spotnext.core.infrastructure.type.AccessorType.set)
    public void setStringValue(String stringValue) {
        this.stringValue = stringValue;
    }

    @Accessor(propertyName = "floatValue", type = io.spotnext.core.infrastructure.type.AccessorType.get)
    public Float getFloatValue() {
        return this.floatValue;
    }

    /**
     * The short description of the configuration entry's purpose.
     */
    @Accessor(propertyName = "description", type = io.spotnext.core.infrastructure.type.AccessorType.set)
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * The config entries referenced by this configuration.
     */
    @Accessor(propertyName = "configuration", type = io.spotnext.core.infrastructure.type.AccessorType.get)
    public Configuration getConfiguration() {
        return this.configuration;
    }

    @Accessor(propertyName = "intValue", type = io.spotnext.core.infrastructure.type.AccessorType.get)
    public Integer getIntValue() {
        return this.intValue;
    }

    @Accessor(propertyName = "floatValue", type = io.spotnext.core.infrastructure.type.AccessorType.set)
    public void setFloatValue(Float floatValue) {
        this.floatValue = floatValue;
    }

    /**
     * The short description of the configuration entry's purpose.
     */
    @Accessor(propertyName = "description", type = io.spotnext.core.infrastructure.type.AccessorType.get)
    public String getDescription() {
        return this.description;
    }

    @Accessor(propertyName = "doubleValue", type = io.spotnext.core.infrastructure.type.AccessorType.get)
    public Double getDoubleValue() {
        return this.doubleValue;
    }

    @Accessor(propertyName = "stringValue", type = io.spotnext.core.infrastructure.type.AccessorType.get)
    public String getStringValue() {
        return this.stringValue;
    }

    /**
     * The config entries referenced by this configuration.
     */
    @Accessor(propertyName = "configuration", type = io.spotnext.core.infrastructure.type.AccessorType.set)
    public void setConfiguration(Configuration configuration) {
        this.configuration = configuration;
    }
}