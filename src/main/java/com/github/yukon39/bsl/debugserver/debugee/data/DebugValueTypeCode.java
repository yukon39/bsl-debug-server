package com.github.yukon39.bsl.debugserver.debugee.data;

import lombok.Getter;

public enum DebugValueTypeCode {
    BOOLEAN (2, "Boolean", "Булево"),
    NUMBER(3, "Number", "Число"),
    VALUE_LIST_ITEM(100, "ValueListItem", "ЭлементСпискаЗначений"),
    ITEM_HORIZONTAL_LOCATION(100, "ItemHorizontalLocation", "ГоризонтальноеПоложениеЭлемента"),
    ITEM_VERTICAL_ALIGN(100, "ItemVerticalAlign", "ВертикальноеПоложениеЭлемента"),
    AUTO_SAVE_FORM_DATA_IN_SETTINGS(100, "AutoSaveFormDataInSettings", "АвтоматическоеСохранениеДанныхФормыВНастройках"),
    VERTICAL_FORM_SCROLL(100, "VerticalFormScroll", "ВертикальнаяПрокруткаФормы"),
    FORM_ITEM_SPACING(100, "FormItemSpacing", "ИнтервалМеждуЭлементамиФормы"),
    CHILD_FORM_ITEMS_GROUP(100, "ChildFormItemsGroup", "ГруппировкаПодчиненныхЭлементовФормы"),
    ITEMS_AND_TITLES_ALIGN_VARIANT(100, "ItemsAndTitlesAlignVariant", "ВариантВыравниванияЭлементовИЗаголовков")
    ;

    @Getter
    private final Integer typeCode;

    @Getter
    private final String typeName;

    @Getter
    private final String typeNameRU;

    DebugValueTypeCode(Integer typeCode, String typeName, String typeNameRU) {
        this.typeCode = typeCode;
        this.typeName = typeName;
        this.typeNameRU = typeNameRU;
    }

}
