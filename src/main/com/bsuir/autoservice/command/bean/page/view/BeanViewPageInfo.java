package main.com.bsuir.autoservice.command.bean.page.view;

import main.com.bsuir.autoservice.bean.Bean;
import main.com.bsuir.autoservice.command.CommandDataTypeRequestParameter;

import java.util.List;

public class BeanViewPageInfo {
    @CommandDataTypeRequestParameter
    String name;
    @CommandDataTypeRequestParameter
    int page;
    @CommandDataTypeRequestParameter
    int countRecords;

    int totalPagesCount;
    List<Bean> beans;
}
