package eu.peppol.statistics;

import java.util.Date;

/**
 * Transforms aggregated statistics into another format. This interface has been designed to maximize performance
 * when transforming the results from the statistics repository into whatever format the implementor of this
 * interface has choosen. I.e. transformation from SQL ResultSet to XML, which will not require any new objects to
 * be created.
 *
 * To use it:
 * <ol>
 *     <li>Invoke {@link #startEntry()}  first</li>
 *     <li>Invoke the writeXxxx methods for each field</li>
 *     <li>Complete the entry by calling {@link #endEntry()}</li>
 * </ol>
 *
 * User: steinar
 * Date: 24.02.13
 * Time: 10:27
 */
public interface StatisticsTransformer {

    String STATISTICS_DOCUMENT_START_ELEMENT_NAME = "peppol-ap-statistics";
    String ENTRY_START_ELEMENT_NAME = "entry";
    String ACCESS_POINT_ID_ELEMENT_NAME = "access-point-id";
    String PARTICIPANT_ID_ELEMENT_NAME = "participant-id";
    String DOCUMENT_TYPE_ELEMENT_NAME = "document-type";
    String PROFILE_ID_ELEMENT_NAME = "profile-id";
    String CHANNEL_ELEMENT_NAME = "channel";
    String COUNT_ELEMENT_NAME = "count";
    String PERIOD_ELEMENT_NAME = "period";
    String DIRECTION_ELEMENT_NAME = "direction";

    void startStatistics(Date start, Date end);

    /** Invoked by the transformer upon the start of a new entry (row, line, etc.) of statistical data */
    void startEntry();

    void writeAccessPointIdentifier(String accessPointIdentifier);

    void writePeriod(String period);

    void writeDirection(String direction);

    void writeParticipantIdentifier(String participantId);

    void writeDocumentType(String documentType);

    void writeProfileId(String profileId);

    void writeChannel(String channel);

    void writeCount(int count);

    /** Completes the current statistics entry */
    void endEntry();

    void endStatistics();
}
