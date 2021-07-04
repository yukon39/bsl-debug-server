package com.github.yukon39.bsl.debugserver.debugee;

import lombok.Getter;

public enum DebugValueTypeCode {
    UNDEFINED(0, "Undefinded", "Неопределено"),

    BOOLEAN (2, "Boolean",
            "\u0411\u0443\u043B\u0435\u0432\u043E"),
    NUMBER(3, "Number", "Число"),
    STRING(4, "String", "Строка"),
    DATE(5, "Date", "Дата"),
    VALUE_LIST_ITEM(100, "ValueListItem",
            "\u042D\u043B\u0435\u043C\u0435\u043D\u0442\u0421\u043F\u0438\u0441\u043A\u0430\u0417\u043D\u0430\u0447\u0435\u043D\u0438\u0439"),
    ITEM_HORIZONTAL_LOCATION(100, "ItemHorizontalLocation", "ГоризонтальноеПоложениеЭлемента"),
    ITEM_VERTICAL_ALIGN(100, "ItemVerticalAlign", "ВертикальноеПоложениеЭлемента"),
    AUTO_SAVE_FORM_DATA_IN_SETTINGS(100, "AutoSaveFormDataInSettings", "АвтоматическоеСохранениеДанныхФормыВНастройках"),
    VERTICAL_FORM_SCROLL(100, "VerticalFormScroll", "ВертикальнаяПрокруткаФормы"),
    FORM_ITEM_SPACING(100, "FormItemSpacing",
            "\u0418\u043D\u0442\u0435\u0440\u0432\u0430\u043B\u041C\u0435\u0436\u0434\u0443\u042D\u043B\u0435\u043C\u0435\u043D\u0442\u0430\u043C\u0438\u0424\u043E\u0440\u043C\u044B"),
    CHILD_FORM_ITEMS_GROUP(100, "ChildFormItemsGroup", "ГруппировкаПодчиненныхЭлементовФормы"),
    ITEMS_AND_TITLES_ALIGN_VARIANT(100, "ItemsAndTitlesAlignVariant",
            "\u0412\u0430\u0440\u0438\u0430\u043D\u0442\u0412\u044B\u0440\u0430\u0432\u043D\u0438\u0432\u0430\u043D\u0438\u044F\u042D\u043B\u0435\u043C\u0435\u043D\u0442\u043E\u0432\u0418\u0417\u0430\u0433\u043E\u043B\u043E\u0432\u043A\u043E\u0432")
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
