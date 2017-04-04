package main.com.bsuir.autoservice.command.bean.page;

import main.com.bsuir.autoservice.bean.Bean;
import main.com.bsuir.autoservice.command.CommandDataTypeRequestParameter;

import java.util.List;

public class BeanPageInfo {
    @CommandDataTypeRequestParameter
    String table;
    @CommandDataTypeRequestParameter
    int page;
    @CommandDataTypeRequestParameter
    int countRecords;

    int totalPagesCount;
    List<Bean> beans;
}
