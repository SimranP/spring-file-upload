package com.example.uploadingfiles.storage;

import com.example.uploadingfiles.storage.dto.Person;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

@Service
public class ExcelService {
    public List<Person> readPersonFile(MultipartFile filename) throws IOException {
        Workbook workbook = new XSSFWorkbook(filename.getInputStream());
        Sheet sheet = workbook.getSheetAt(0);
        Iterator<Row> iterator = sheet.iterator();
        List<Person> list = new java.util.ArrayList<>(Collections.emptyList());

        while(iterator.hasNext()) {
            Person person = read(iterator.next());
            System.out.println(person);
            list.add(person);
        }

        return list;
    }

    private Person read(Row row){
        String firstName = row.getCell(0).toString();
        String lastName = row.getCell(1).toString();
        System.out.println(firstName);
        System.out.println(lastName);
        return new Person(firstName, lastName);
    }
}
