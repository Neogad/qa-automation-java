package com.tinkoff.edu.app.repositories;

import com.tinkoff.edu.app.LoanRequest;
import com.tinkoff.edu.app.LoanResponce;
import com.tinkoff.edu.app.enums.LoanResponceType;
import com.tinkoff.edu.app.enums.LoanType;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.Collectors;

import static java.nio.file.StandardOpenOption.*;

public class FileLoanCalcRepository implements LoanCalcRepository {

    LoanResponce loanResponce;
    UUID requestId;
    String filePath = "src/main/java/com/tinkoff/edu/app/Files/";
    Map<UUID, LoanResponce> loanResponses = new HashMap();
    Map<UUID, LoanRequest> loanRequestes = new HashMap();
    final String loanResponseFile = "LoanResponse.txt";
    final String loanRequestFile = "LoanRequest.txt";



    @Override
    public LoanResponce save(LoanRequest loanRequest, LoanResponceType responceType) {
        requestId = UUID.randomUUID();
        loanResponce = new LoanResponce(responceType, requestId);
        loanRequest.setRequestId(requestId);
        writeFile(loanRequestFile, loanRequest.toString());
        writeFile(loanResponseFile, loanResponce.toString());
        return loanResponce;
    }


    void writeFile(String fileName, String line) {
        line += System.lineSeparator();
        try {
            Path path = Path.of(filePath + fileName);
            Files.writeString(path, line, APPEND, CREATE, WRITE);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    void readFile(String fileName) throws IOException {
        Path path = Path.of(filePath + fileName);
        final List<String> lines = Files.readAllLines(path);
        if (Objects.equals(fileName, loanResponseFile)) {
            for (String line : lines) {
                LoanResponce loanResponce = parseLoanResponse(line);
                loanResponses.put(loanResponce.getRequestId(), loanResponce);
            }
        } else {
            for (String line : lines) {
                LoanRequest loanRequest = parseLoanRequest(line);
                loanRequestes.put(loanResponce.getRequestId(), loanRequest);
            }
        }
    }

    LoanRequest parseLoanRequest(String line) {
        String[] resultList = parseString(line);
        return new LoanRequest(UUID.fromString(resultList[0]), resultList[1], LoanType.valueOf(resultList[2]), Integer.parseInt(resultList[3]), Integer.parseInt(resultList[4]));
    }

    LoanResponce parseLoanResponse(String line) {
        String[] resultList = parseString(line);
        return new LoanResponce(LoanResponceType.valueOf(resultList[1]), UUID.fromString(resultList[0]));
    }

    String[] parseString(String line) {
        String[] resultList ;
        resultList = line.split(";");
        return resultList;
    }


    @Override
    public LoanResponce getResponce(UUID uuid) {
        readFiles();
        return loanResponses.get(uuid);
    }

    @Override
    public void updateResponce(UUID requestId, LoanResponceType loanResponceType) {
        readFiles();
        loanResponce = loanResponses.get(requestId);
        loanResponce.setLoanResponceType(loanResponceType);
        loanResponses.put(requestId, loanResponce);
        for (LoanResponce loanResponseValue: loanResponses.values() ){
            writeFile(loanResponseFile,loanResponseValue.toString());
        }
    }

    @Override
    public LoanResponce getResponce(LoanType loanType) {
        readFiles();
        return getLoanResponce(loanType, loanRequestes, loanResponses);
    }

    static LoanResponce getLoanResponce(LoanType loanType, Map<UUID, LoanRequest> loanRequestes, Map<UUID, LoanResponce> loanResponses) {
        List<UUID> loanReq = loanRequestes.entrySet()
                .stream()
                .filter(loanRequestEntry -> Objects.equals(loanRequestEntry.getValue().getLoanType(), loanType))
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());


        return loanResponses.entrySet()
                .stream()
                .filter(loanResponceEntry -> Objects.equals(loanResponceEntry.getValue().getRequestId(), loanReq.get(0)))
                .map(Map.Entry::getValue)
                .collect(Collectors.toList()).get(0);
    }

    public void readFiles() {
        try {
            readFile(loanRequestFile);
            readFile(loanResponseFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
