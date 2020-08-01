package com.github.yukon39.bsl.debugserver.context;

import com.github.yukon39.bsl.debugserver.debugee.debugBaseData.BSLModuleIdInternal;
import com.github.yukon39.bsl.debugserver.debugee.debugBaseData.BSLModuleType;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

public class SourceManagerTest {

    private static BSLModuleIdInternal moduleIdCatalog() {

        var moduleId = new BSLModuleIdInternal();
        moduleId.setType(BSLModuleType.CONFIG_MODULE);
        moduleId.setObjectID(UUID.fromString("3bf89955-e598-45e7-aabf-9a7f63a888b1"));
        moduleId.setPropertyID(ModulePropertyId.MANAGER_MODULE.getId());

        return moduleId;
    }

    @Test
    void testSourceXMLConfiguration() {

        // given
        File srcPath = new File("src/test/resources/src/cf/xml");
        var sourceManager = SourceManager.create();
        sourceManager.setConfigurationSource(srcPath.toPath().toAbsolutePath());
        var moduleId = moduleIdCatalog();

        // when
        var source = sourceManager.getSource("Catalogs/Справочник1/Ext/ManagerModule.bsl");

        // then
        assertThat(source).isNotNull();
        assertThat(source.getName()).isEqualTo("ManagerModule.bsl");

        var adapterData = (BSLModuleIdInternal) source.getAdapterData();
        assertThat(moduleId.isEqualModuleId(adapterData)).isTrue();

        // when
        source = sourceManager.getSource(moduleId);

        // then
        assertThat(source).isNotNull();
    }

    @Test
    void testSourceEDTConfiguration() {

        // given
        File srcPath = new File("src/test/resources/src/cf/edt");
        var sourceManager = SourceManager.create();
        sourceManager.setConfigurationSource(srcPath.toPath().toAbsolutePath());
        var moduleId = moduleIdCatalog();

        // when
        var source = sourceManager.getSource("src/Catalogs/Справочник1/ManagerModule.bsl");

        // then
        assertThat(source).isNotNull();
        assertThat(source.getName()).isEqualTo("ManagerModule.bsl");

        var adapterData = (BSLModuleIdInternal) source.getAdapterData();
        assertThat(moduleId.isEqualModuleId(adapterData)).isTrue();

        // when
        source = sourceManager.getSource(moduleId);

        // then
        assertThat(source).isNotNull();
    }
}
