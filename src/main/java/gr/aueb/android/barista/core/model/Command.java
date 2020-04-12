package gr.aueb.android.barista.core.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import gr.aueb.android.barista.core.executor.CommandClient;
import gr.aueb.android.barista.core.executor.CommandExecutor;

import java.util.stream.Stream;

/**
 *  Command interface. Is the most generic representation of a command object.
 *  Specifies all the functions to be implemented by any command object.
 */
@JsonTypeInfo(use=JsonTypeInfo.Id.NAME, include=JsonTypeInfo.As.PROPERTY, property="type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = Auth.class),
        @JsonSubTypes.Type(value = BatteryCharge.class),
        @JsonSubTypes.Type(value = BatteryLevel.class),
        @JsonSubTypes.Type(value = BatteryStatus.class),
        @JsonSubTypes.Type(value = GeoFix.class),
        @JsonSubTypes.Type(value = GetOrientation.class),
        @JsonSubTypes.Type(value = GpsState.class),
        @JsonSubTypes.Type(value = GpsStatus.class),
        @JsonSubTypes.Type(value = LogcatCrash.class),
        @JsonSubTypes.Type(value = LogcatCrashClear.class),
        @JsonSubTypes.Type(value = Monkey.class),
        @JsonSubTypes.Type(value = SvcData.class),
        @JsonSubTypes.Type(value = SvcWifi.class)
})
public interface Command {

    /**
     *  Getter function that returns the sessionToken encapsulated inside a command. The sessionToken is used
     *  to determine the emulator that the command request is originated.
     *
     * @return The SessionToken
     */
    String getSessionToken();

    /**
     *  Function that returns the string representation of a command. This string will be used to create
     *  a system process for execution ;
     *
     * @return The command to be executed in the command line
     */
    @JsonIgnore
    String getCommandString();

    /**
     *  Function that returns the ammount of the delay (in ms) that the execution thread should wait after
     *  the execution of a command. Delay is used when one wants to create technical delay between execution
     *  of many commands in a row
     *
     * @return The delay ammount in ms
     */
    int getDelay();

    /**
     * Specify the delay to be applied to the execution thread after the execution of this command.
     *
     * @param delay The delay ammount in ms
     */
    void setDelay(int delay);


    /**
     * Setter method for the sessionToken member field
     *
     * @param sessionToken The sessionToken field
     */
    void setSessionToken(String sessionToken);

    /**
     *  Function that allows a CommandExecutor to be injected at a Command instance in order for it to decide
     *  which CommandClient to use depending on its type (adb or telnet)
     *
     * @param executor A CommandExecutor instance that knows hoow to execute telnet and adb commands.
     */
    void accept(CommandExecutor executor);

    /**
     *  Function that asserts the succesful execution of the command. The assertion is made by 'asking' the
     *  emulator the real value of a specific system attribute and cheks if this value is same as the expected one.
     *
     * @param client
     * @return True if the real value is the same as the expected one, false oterwise
     */
    boolean isCompleted(CommandClient client);

    /**
     * Function that parses the result of the execution. The result may be used for setting variables.
     * It it mostly used for commands that retrieve information from the emulator.
     *
     * @param resultLines The lines returned from the system after execution of the command
     */
    void parseResult(Stream<String> resultLines);

}
