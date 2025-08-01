package Controller;

import Model.DAO.IOLogDao;

public class IOLogController {
    // 싱글톤
    private IOLogController(){}
    private static final IOLogController instance = new IOLogController();
    public static IOLogController getInstance(){
        return instance;
    }

    // dao 싱글톤 호출
    private IOLogDao ioLogDao = IOLogDao.getInstance();

    //
}
