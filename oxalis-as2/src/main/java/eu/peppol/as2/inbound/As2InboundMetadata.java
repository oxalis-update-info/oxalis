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

package eu.peppol.as2.inbound;

import eu.peppol.identifier.MessageId;
import no.difi.oxalis.api.inbound.InboundMetadata;
import no.difi.oxalis.api.timestamp.Timestamp;
import no.difi.vefa.peppol.common.model.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

class As2InboundMetadata implements InboundMetadata {

    private final MessageId messageId;

    private final Header header;

    private final Date timestamp;

    private final TransportProfile transportProfile;

    private final Digest digest;

    private final Receipt primaryReceipt = null;

    private final List<Receipt> receipts;

    public As2InboundMetadata(MessageId messageId, Header header, Timestamp timestamp,
                              TransportProfile transportProfile, Digest digest) {
        this.messageId = messageId;
        this.header = header;
        this.timestamp = timestamp.getDate();
        this.transportProfile = transportProfile;
        this.digest = digest;

        List<Receipt> receipts = new ArrayList<>();
        if (timestamp.getReceipt().isPresent())
            receipts.add(timestamp.getReceipt().get());
        this.receipts = Collections.unmodifiableList(receipts);
    }

    @Override
    public MessageId getMessageId() {
        return messageId;
    }

    @Override
    public Header getHeader() {
        return header;
    }

    @Override
    public Date getTimestamp() {
        return timestamp;
    }

    @Override
    public TransportProfile getProtocol() {
        return transportProfile;
    }

    @Override
    public TransportProtocol getTransportProtocol() {
        return TransportProtocol.AS2;
    }

    @Override
    public Digest getDigest() {
        return digest;
    }

    @Override
    public List<Receipt> getReceipts() {
        return receipts;
    }

    @Override
    public Receipt primaryReceipt() {
        return primaryReceipt;
    }
}