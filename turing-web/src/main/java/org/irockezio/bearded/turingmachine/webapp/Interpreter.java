package org.irockezio.bearded.turingmachine.webapp;

import org.irockezio.bearded.turingmachine.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.List;

public class Interpreter extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String programParam;
        String tapeParam;
        if (req.getParameter("tape") != null && req.getParameter("program") != null) {
            programParam = req.getParameter("program").toString();
            tapeParam = req.getParameter("tape").toString();
        } else {
            throw new ServletException("Tape or program is empty");
        }


        TuringMachineCellValue[] values = new TuringMachineCellValue[tapeParam.length()];

        int i = 0;
        for (char c : tapeParam.toCharArray()) {
            values[i] = new TuringMachineCellValue(c);
            i++;
        }
        TuringMachineTape tape = new TuringMachineTape(values);
        TuringMachineProgram program = new TuringMachineProgram();

        BufferedReader reader = new BufferedReader(new StringReader(programParam));

        String line;
        while ((line = reader.readLine()) != null) {
            try {
                List<TuringMachineInstruction> instructions = TuringMachineInstruction.parseInstructions(line);
                for (TuringMachineInstruction instruction : instructions) {
                    program.addInstruction(instruction);
                }
            } catch (InvalidCommand invalidCommand) {
                throw new ServletException("Invalid command:" + invalidCommand.getCommand());
            }
        }

        TuringMachineTape result;
        try {
            result = TuringMachine.runProgram(program, tape);
        } catch (BadProgram badProgram) {
            throw new ServletException("Invalid program:" + badProgram.getMessage());
        } catch (BadTape badTape) {
            throw new ServletException("Invalid tape:" + badTape.getMessage());
        }
        req.setAttribute("result", result);

        getServletContext().getRequestDispatcher("/result.jsp").forward(req, resp);
    }
}
