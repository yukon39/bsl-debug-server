@XmlSchema(namespace = "http://v8.1c.ru/8.3/debugger/debugDBGUICommands",
        xmlns = {
                @XmlNs(namespaceURI = "http://v8.1c.ru/8.3/debugger/debugBaseData", prefix = "debugBaseData"),
                @XmlNs(namespaceURI = "http://v8.1c.ru/8.3/debugger/debugMeasure", prefix = "debugMeasure"),
                @XmlNs(namespaceURI = "http://v8.1c.ru/8.3/debugger/debugRTEInfo", prefix = "debugRTEInfo"),
                @XmlNs(namespaceURI = "http://v8.1c.ru/8.3/debugger/debugForegroundData", prefix = "debugForegroundData"),
                @XmlNs(namespaceURI = "http://v8.1c.ru/8.3/debugger/debugBreakpoints", prefix = "debugBreakpoints"),
                @XmlNs(namespaceURI = "http://v8.1c.ru/8.3/debugger/debugCalculations", prefix = "debugCalculations")
        },
        elementFormDefault = XmlNsForm.QUALIFIED)
@ParametersAreNonnullByDefault
package com.github.yukon39.bsl.debugserver.debugee.debugDBGUICommands;

import jakarta.xml.bind.annotation.XmlNs;
import jakarta.xml.bind.annotation.XmlNsForm;
import jakarta.xml.bind.annotation.XmlSchema;

import javax.annotation.ParametersAreNonnullByDefault;