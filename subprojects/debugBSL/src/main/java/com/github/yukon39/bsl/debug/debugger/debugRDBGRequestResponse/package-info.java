@XmlSchema(namespace = "http://v8.1c.ru/8.3/debugger/debugRDBGRequestResponse",
        xmlns = {
                @XmlNs(namespaceURI = "http://v8.1c.ru/8.1/data/core", prefix = "core"),
                @XmlNs(namespaceURI = "http://v8.1c.ru/8.3/debugger/debugRDBGRequestResponse", prefix = "debugRDBGRequestResponse"),
                @XmlNs(namespaceURI = "http://v8.1c.ru/8.3/debugger/debugBaseData", prefix = "debugBaseData"),
                @XmlNs(namespaceURI = "http://v8.1c.ru/8.3/debugger/debugRTEFilter", prefix = "debugRTEFilter"),
                @XmlNs(namespaceURI = "http://v8.1c.ru/8.3/debugger/debugMeasure", prefix = "debugMeasure"),
                @XmlNs(namespaceURI = "http://v8.1c.ru/8.3/debugger/debugRTEInfo", prefix = "debugRTEInfo"),
                @XmlNs(namespaceURI = "http://v8.1c.ru/8.3/debugger/debugAutoAttach", prefix = "debugAutoAttach"),
                @XmlNs(namespaceURI = "http://v8.1c.ru/8.3/debugger/debugForegroundData", prefix = "debugForegroundData"),
                @XmlNs(namespaceURI = "http://v8.1c.ru/8.3/debugger/debugBreakpoints", prefix = "debugBreakpoints"),
                @XmlNs(namespaceURI = "http://v8.1c.ru/8.3/debugger/debugDBGUICommands", prefix = "debugDBGUICommands"),
                @XmlNs(namespaceURI = "http://v8.1c.ru/8.3/debugger/debugArea", prefix = "debugArea"),
                @XmlNs(namespaceURI = "http://v8.1c.ru/8.3/debugger/debugCalculations", prefix = "debugCalculations")
        },
        elementFormDefault = XmlNsForm.QUALIFIED
)
package com.github.yukon39.bsl.debug.debugger.debugRDBGRequestResponse;

import jakarta.xml.bind.annotation.XmlNs;
import jakarta.xml.bind.annotation.XmlNsForm;
import jakarta.xml.bind.annotation.XmlSchema;