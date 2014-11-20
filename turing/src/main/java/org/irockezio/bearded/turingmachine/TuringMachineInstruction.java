package org.irockezio.bearded.turingmachine;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TuringMachineInstruction {

    public static final int HALT_STATE = -1;
    private static String COMMAND_PATTERN = "(\\d+) (\\w?) (\\w?) (l|r|n) (\\d+|halt)";
    private final int state;
    private final TuringMachineCellValue sourceSymbol;
    private final TuringMachineCellValue destinationSymbol;
    private final TapeDirection direction;
    private final int nextState;

    public TuringMachineInstruction(
            int state, TuringMachineCellValue sourceSymbol,
            TuringMachineCellValue destinationSymbol,
            TapeDirection direction,
            int nextState
    ) {
        this.state = state;
        this.sourceSymbol = sourceSymbol;
        this.destinationSymbol = destinationSymbol;
        this.direction = direction;
        this.nextState = nextState;
    }

    public static List<TuringMachineInstruction> parseInstructions(String... commands) throws InvalidCommand {
        Pattern pattern = Pattern.compile(COMMAND_PATTERN);
        List<TuringMachineInstruction> result = new ArrayList<>();

        for (String command : commands) {
            Matcher matcher = pattern.matcher(command);

            if (matcher.find()) {
                String state = matcher.group(1);
                Integer parsedState = Integer.parseInt(state);

                String nextState = matcher.group(5);
                Integer parsedNextState;
                if (!nextState.equals("halt")) {
                    parsedNextState = Integer.parseInt(nextState);
                } else {
                    parsedNextState = HALT_STATE;
                }

                String direction = matcher.group(4);
                TapeDirection parsedDirection;
                //TODO:rewrite
                switch (direction) {
                    case "l":
                        parsedDirection = TapeDirection.LEFT;
                        break;
                    case "r":
                        parsedDirection = TapeDirection.RIGHT;
                        break;
                    case "n":
                        parsedDirection = TapeDirection.NONE;
                        break;
                    default:
                        throw new RuntimeException("Unknown state:" + direction);
                }

                TuringMachineCellValue parsedSource =
                        new TuringMachineCellValue(
                                (!matcher.group(2).isEmpty()) ? matcher.group(2).charAt(0) : null
                        );
                TuringMachineCellValue parsedDestination = new TuringMachineCellValue(
                        (!matcher.group(3).isEmpty()) ? matcher.group(3).charAt(0) : null
                );
                TuringMachineInstruction instruction = new TuringMachineInstruction(
                        parsedState,
                        parsedSource,
                        parsedDestination,
                        parsedDirection,
                        parsedNextState
                );
                result.add(instruction);
            } else {
                throw new InvalidCommand(command);
            }
        }

        return result;
    }

    public TuringMachineCellValue getSourceSymbol() {
        return sourceSymbol;
    }

    public TuringMachineCellValue getDestinationSymbol() {
        return destinationSymbol;
    }

    public TapeDirection getDirection() {
        return direction;
    }

    public int getNextState() {
        return nextState;
    }

    public int getState() {
        return state;
    }

    @Override
    public boolean equals(Object object) {
        if (object == null || !(object instanceof TuringMachineInstruction)) {
            return false;
        }
        TuringMachineInstruction compared = (TuringMachineInstruction) object;
        return (
                this.getDirection().equals(compared.getDirection()) &&
                        this.getState() == compared.getState() &&
                        this.getSourceSymbol().equals(compared.getSourceSymbol()) &&
                        this.getDirection().equals(compared.getDirection()) &&
                        this.getNextState() == compared.getNextState() &&
                        this.getDestinationSymbol().equals(compared.getDestinationSymbol())
        );
    }
}
