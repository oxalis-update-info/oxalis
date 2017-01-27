/*
 * Copyright 2010-2017 Norwegian Agency for Public Management and eGovernment (Difi)
 *
 * Licensed under the EUPL, Version 1.1 or – as soon they
 * will be approved by the European Commission - subsequent
 * versions of the EUPL (the "Licence");
 *
 * You may not use this work except in compliance with the Licence.
 *
 * You may obtain a copy of the Licence at:
 *
 * https://joinup.ec.europa.eu/community/eupl/og_page/eupl
 *
 * Unless required by applicable law or agreed to in
 * writing, software distributed under the Licence is
 * distributed on an "AS IS" basis,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either
 * express or implied.
 * See the Licence for the specific language governing
 * permissions and limitations under the Licence.
 */

package eu.peppol.outbound;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Module;
import eu.peppol.as2.outbound.As2OutboundModule;
import eu.peppol.outbound.lookup.LookupModule;
import eu.peppol.outbound.transmission.TransmissionModule;
import eu.peppol.outbound.transmission.TransmissionRequestBuilder;
import eu.peppol.outbound.transmission.TransmissionRequestFactory;
import eu.peppol.persistence.guice.OxalisDataSourceModule;
import eu.peppol.persistence.guice.OxalisRepositoryModule;
import eu.peppol.util.GlobalConfiguration;
import eu.peppol.util.OxalisKeystoreModule;
import eu.peppol.util.OxalisProductionConfigurationModule;
import no.difi.oxalis.api.evidence.EvidenceFactory;
import no.difi.oxalis.api.lookup.LookupService;
import no.difi.oxalis.api.outbound.TransmissionService;
import no.difi.oxalis.api.outbound.Transmitter;
import no.difi.oxalis.commons.evidence.EvidenceModule;
import no.difi.oxalis.commons.http.ApacheHttpModule;
import no.difi.oxalis.commons.mode.ModeModule;
import no.difi.oxalis.commons.plugin.PluginModule;
import no.difi.oxalis.commons.statistics.StatisticsModule;
import no.difi.oxalis.commons.timestamp.TimestampModule;
import no.difi.oxalis.commons.tracing.TracingModule;

import java.util.Arrays;
import java.util.List;

/**
 * Entry point and Object factory for the Oxalis outbound module.
 * <p>
 * Google guice is very lightweight, so there is really no need to make this a singleton in order to optimize for performance.
 *
 * @author steinar
 * @author thore
 * @author erlend
 */
public class OxalisOutboundComponent {

    private Injector injector;

    public OxalisOutboundComponent() {

        List<Module> modules = Arrays.asList(
                new OxalisProductionConfigurationModule(),
                new OxalisKeystoreModule(),
                new TracingModule(),
                new ModeModule(),
                new LookupModule(),
                new OxalisDataSourceModule(),
                new ApacheHttpModule(),
                new As2OutboundModule(),
                new OxalisRepositoryModule(),
                new TransmissionModule(),
                new EvidenceModule(),
                new TimestampModule(),
                new StatisticsModule(),
                new PluginModule()
        );

        injector = Guice.createInjector(modules);
    }

    /**
     * Retrieves instances of TransmissionRequestBuilder, while not exposing Google Guice to the outside
     *
     * @return instance of TransmissionRequestBuilder
     */
    public TransmissionRequestBuilder getTransmissionRequestBuilder() {
        return injector.getInstance(TransmissionRequestBuilder.class);
    }

    public TransmissionRequestFactory getTransmissionRequestFactory() {
        return injector.getInstance(TransmissionRequestFactory.class);
    }

    /**
     * Retrieves instance of LookupService, without revealing intern object dependency injection.
     */
    public LookupService getLookupService() {
        return injector.getInstance(LookupService.class);
    }

    /**
     * Retrieves instance of DefaultTransmitter, without revealing intern object dependency injection.
     *
     * @return instance of Transmitter
     */
    public Transmitter getTransmitter() {
        return injector.getInstance(Transmitter.class);
    }

    /**
     * Provides access to the Global configuration being used.
     *
     * @return
     */
    public GlobalConfiguration getGlobalConfiguration() {
        return injector.getInstance(GlobalConfiguration.class);
    }

    public EvidenceFactory getEvidenceFactory() {
        return injector.getInstance(EvidenceFactory.class);
    }

    /**
     * Provides access to the Google Guice injector in order to reuse the component with other components that also uses
     * Google Guice.
     *
     * @return
     */
    public Injector getInjector() {
        return injector;
    }

    public TransmissionService getTransmissionService() {
        return injector.getInstance(TransmissionService.class);
    }
}