package dev.karatay.commandexecutor.core.executor.impl;

import dev.karatay.commandexecutor.config.model.CommandElement;
import dev.karatay.commandexecutor.core.executor.Executor;
import dev.karatay.commandexecutor.domain.model.OSType;
import dev.karatay.commandexecutor.error.CommandErrors;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class LinkExecutor implements Executor {

    private CommandElement commandElement;

    @Override
    public void execute() {
        commandElement.validate();
        var url = formatUrl(commandElement);
        var pb = buildProcess(url);
        try {
            pb.start();
        } catch (Exception ex) {
            throw CommandErrors.commandRunError(commandElement, ex);
        }
    }

    private String formatUrl(CommandElement commandElement) {
        var url = commandElement.getValue();
        if (!url.startsWith("http://") && !url.startsWith("https://")) {
            url = "https://" + url;
        }
        return url;
    }

    private ProcessBuilder buildProcess(String url) {
        var osType = OSType.getOSType();
        return switch (osType) {
            case Windows -> new ProcessBuilder("cmd", "/c", "start", url);
            case Linux -> new ProcessBuilder("xdg-open", url);
            default -> new ProcessBuilder("open", url);
        };
    }
}
