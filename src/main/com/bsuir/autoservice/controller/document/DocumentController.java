package main.com.bsuir.autoservice.controller.document;

import com.google.inject.Inject;
import main.com.bsuir.autoservice.binding.annotation.action.map.DocumentActionMap;
import main.com.bsuir.autoservice.command.ret.DocumentRet;
import main.com.bsuir.autoservice.controller.AbstractActionController;
import main.com.bsuir.autoservice.controller.action.Action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.util.Map;

public class DocumentController<R> extends AbstractActionController<DocumentRet> {

    @Inject
    public DocumentController(@DocumentActionMap Map<String, Action> actionMap) {
        super(actionMap);
    }

    @Override
    public final void returnResultImpl(HttpServletRequest request, HttpServletResponse response, DocumentRet resultData) throws Exception {
        response.setContentType(resultData.contentType);
        response.setHeader("Content-Disposition", "inline; filename=" + resultData.fileName);
        OutputStream out = response.getOutputStream();
        resultData.documentStream.writeTo(out);
        out.flush();
        out.close();
    }
}
