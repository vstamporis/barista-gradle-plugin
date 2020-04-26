package gr.aueb.android.barista.core.model;

import com.fasterxml.jackson.annotation.JsonTypeName;
import gr.aueb.android.barista.core.executor.CommandClient;
import gr.aueb.android.barista.utilities.BaristaCommandPrefixes;
import gr.aueb.android.barista.utilities.BaristaLogger;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@JsonTypeName("LogcatCrash")
public class LogcatCrash extends AbstractAdbCommand {

    private String apk;
    private boolean exception = false;
    private boolean thisApk = false;
    private List<String> exceptions = null, result = null;

    public LogcatCrash(String token, String apk) {
        super(token);
        this.apk = apk;
    }

    @Override
    public String getCommandString() {
        return BaristaCommandPrefixes.LOGCAT_CRASH + " -d";
    }

    @Override
    public void parseResult(Stream<String> resultLines) {
        this.exceptions = resultLines.collect(Collectors.toList());
        this.exceptions.stream().filter(new Predicate<String>() {
            @Override
            public boolean test(String s) {
                return s.contains("FATAL EXCEPTION");
            }
        }).forEach(new Consumer<String>() {
            @Override
            public void accept(String s) {
                exception = true;
            }
        });
        this.exceptions.stream().filter(new Predicate<String>() {
            @Override
            public boolean test(String s) {
                return s.contains(apk);
            }
        }).forEach(new Consumer<String>() {
            @Override
            public void accept(String s) {
                thisApk = true;
            }
        });
        if (this.exception && this.thisApk) {
            this.result = new ArrayList<>();
            this.exceptions.stream().forEach(i -> {
                String temp = i.substring(i.indexOf("AndroidRuntime:"));
                temp = temp.trim();
                temp = temp.substring(16);
                result.add(temp);
            });
        }
    }

    @Override
    public boolean isCompleted(CommandClient client) {
//        LogcatCrashClear clear = new LogcatCrashClear(this.getSessionToken());
//        client.executeCommand(clear);
        return true;
    }

    public boolean hasCrashed() {
        return this.exception && this.thisApk;
    }

    public List<String> getCrashLog() {
        return this.result;
    }

    public String getApk() {
        return apk;
    }

    public void setApk(String apk) {
        this.apk = apk;
    }

    public boolean isException() {
        return exception;
    }

    public void setException(boolean exception) {
        this.exception = exception;
    }

    public boolean isThisApk() {
        return thisApk;
    }

    public void setThisApk(boolean thisApk) {
        this.thisApk = thisApk;
    }

    public List<String> getExceptions() {
        return exceptions;
    }

    public void setExceptions(List<String> exceptions) {
        this.exceptions = exceptions;
    }

    public List<String> getResult() {
        return result;
    }

    public void setResult(List<String> result) {
        this.result = result;
    }
}
