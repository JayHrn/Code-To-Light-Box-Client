package com.nchu.software.pattern.composite;

import com.nchu.software.pattern.command.Command;

/**
 * @Author JayHrn
 * @Date 2023-05-18 14:57
 * @Description
 */
public class Leaf extends FunctionComponent {
    private Command command;

    public Leaf(Command command) {
        this.command = command;
    }

    @Override
    public boolean execute() {
        if (command != null) {
            return command.execute();
        }
        return false;
    }

    public Command getCommand() {
        return command;
    }

    public void setCommand(Command command) {
        this.command = command;
    }
}
