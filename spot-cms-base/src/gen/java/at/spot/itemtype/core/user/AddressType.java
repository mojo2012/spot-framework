/**
 * This file is auto-generated. All changes will be overwritten.
 */
package at.spot.itemtype.core.user;

import at.spot.core.infrastructure.annotation.GetProperty;
import at.spot.core.infrastructure.annotation.ItemType;
import at.spot.core.infrastructure.annotation.Property;
import at.spot.core.infrastructure.annotation.SetProperty;

import at.spot.itemtype.core.UniqueIdItem;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;


@ItemType(typeCode = "addresstype")
@SuppressFBWarnings({"MF_CLASS_MASKS_FIELD", "EI_EXPOSE_REP", "EI_EXPOSE_REP2"})
public class AddressType extends UniqueIdItem {
    private static final long serialVersionUID = -1L;

    /** The name of the address type. */
    @Property
    protected String name;

    @GetProperty
    public String getName() {
        return this.name;
    }

    @SetProperty
    public void setName(String name) {
        this.name = name;
        markAsDirty("name");
    }
}