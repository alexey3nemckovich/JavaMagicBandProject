package main.com.bsuir.autoservice.command.bean.table;

import main.com.bsuir.autoservice.bean.Bean;
import main.com.bsuir.autoservice.command.CommandDataTypeRequestParameter;

import java.util.List;

public class BeanTablePageInfo {
    @CommandDataTypeRequestParameter
    String name;
    @CommandDataTypeRequestParameter
    int page;
    @CommandDataTypeRequestParameter
    int countRecords;

    int totalPagesCount;
    List<Bean> beans;
}
