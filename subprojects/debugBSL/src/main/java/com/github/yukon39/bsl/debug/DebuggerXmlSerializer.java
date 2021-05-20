package com.github.yukon39.bsl.debug;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBElement;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;

import javax.xml.namespace.QName;
import javax.xml.transform.stream.StreamSource;
import java.io.ByteArrayInputStream;
import java.io.StringReader;
import java.io.StringWriter;

public class DebuggerXmlSerializer {

    public String serialize(Object object) throws DebuggerException {

        try {
            var T = object.getClass();

            var qName = new QName("http://v8.1c.ru/8.3/debugger/debugBaseData", "request");

            var jaxbElement = new JAXBElement<>(qName, (Class<Object>) T, object);

            var jax = JAXBContext.newInstance(T);
            var marshaller = jax.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

            var stringWriter = new StringWriter();
            marshaller.marshal(jaxbElement, stringWriter);
            return stringWriter.toString();

        } catch (JAXBException e) {
            throw new DebuggerException(e);
        }
    }

    public <T> T deserialize(String xmlString, Class<T> objectType) throws DebuggerException {

        var stringReader = new StringReader(xmlString);
        var streamSource = new StreamSource(stringReader);

        return this.deserialize(streamSource, objectType);
    }

    public <T> T deserialize(byte[] xmlBytes, Class<T> objectType) throws DebuggerException {

        var stringReader = new ByteArrayInputStream(xmlBytes);
        var streamSource = new StreamSource(stringReader);

        return this.deserialize(streamSource, objectType);
    }

    public <T> T deserialize(StreamSource streamSource, Class<T> objectType) throws DebuggerException {

        try {
            var jax = JAXBContext.newInstance(objectType);
            var unmarshaller = jax.createUnmarshaller();

            var jaxbElement = unmarshaller.unmarshal(streamSource, objectType);
            return jaxbElement.getValue();
        } catch (JAXBException e) {
            throw new DebuggerException(e);
        }
    }
}
