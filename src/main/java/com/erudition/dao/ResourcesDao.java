package com.erudition.dao;

import com.erudition.bean.CategoryEntity;
import com.erudition.bean.FilesEntity;
import com.erudition.bean.LogEntity;
import com.erudition.bean.UserEntity;
import com.erudition.page.Page;
import com.erudition.page.PageHandler;
import com.erudition.util.MultipartFileUtils;
import com.erudition.util.ProduceThumb;
import com.erudition.util.nlpir.WordFrequency;
import org.hibernate.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by tsj on 16-5-3.
 */
@Repository("resourcesDao")
public class ResourcesDao extends BaseDao {


    @Autowired
    @Qualifier("categoryDao")
    CategoryDao categoryDao;


    @Autowired
    private PageHandler pageHandler;

    public FilesEntity getById(int id) {
        return get(FilesEntity.class, id);
    }

    public Page<FilesEntity> getResourcesByPage(int pageNum, int pageSize, int thirdId) {
        String hql = "from FilesEntity as files where files.categoryId=?";
        Query query = query(hql);
        query.setInteger(0, thirdId);
        return pageHandler.getPage(pageNum, pageSize,
                FilesEntity.class, query);
    }

    public Page<FilesEntity> getResourcesByKeyword(int pageNum, int pageSize, String key) {
        String hql = "from FilesEntity as files where files.keywords like ?";
        Query query = query(hql);
        query.setString(0, "%" + key + "%");
        return pageHandler.getPage(pageNum, pageSize,
                FilesEntity.class, query);
    }

    public List<FilesEntity> getRelationFileByOne(List<FilesEntity> files){

        List<FilesEntity> relationFiles = new ArrayList<>();

        List<FilesEntity> filesExits = new ArrayList<>();

        for(FilesEntity file : files) {
            filesExits.add(file);
        }

            for(FilesEntity file : files){

            String relations = file.getRelations();
            System.out.println("relations: "+relations);
            if(relations != null){

                String [] relationsarr = relations.split(",");
                for(String re:relationsarr){
                    int re_id = Integer.parseInt(re);
                    boolean flag = true;
                    if(re != ""){
                        for (FilesEntity fileExits : filesExits){
                            if(fileExits.getId()==re_id) {
                                flag = false;
                                break;
                            }
                        }
                        if (!flag)
                            break;
                        relationFiles.add(getById(re_id));
                        filesExits.add(getById(re_id));
                        System.out.println("re  :"+re);
                        //break;//只添加一次
                    }
                }
            }

        }
        if(relationFiles==null)
            return null;

        return relationFiles;
    }


    public String saveFiles(String cate1, String cate2, String cate3, String keywords ,String originalName, MultipartFile file, UserEntity user){
        FilesEntity fileEntity = new FilesEntity();

        fileEntity.setTitle(originalName);
        fileEntity.setCreateTime(new Timestamp(System.currentTimeMillis()));
        System.out.println("222222222");
        fileEntity.setSize(exchangeSize(file.getSize()));
        System.out.println("3333333: "+user.getUserName());
        fileEntity.setCreater(user.getUserName());

        System.out.println("111111111");


        CategoryEntity category = categoryDao.getById(Integer.valueOf(cate3));




        //设置文件类型
        String type = originalName.substring(originalName.indexOf('.')+1,originalName.length());
        fileEntity.setType(type);

        fileEntity.setCategoryId(category.getId());
        fileEntity.setCategoryName(category.getCategoryName());

        String saveLocalUrl = categoryDao.getById(Integer.valueOf(cate1)).getCategoryName() + "/" +
                categoryDao.getById(Integer.valueOf(cate2)).getCategoryName() + "/" + category.getCategoryName();


        System.out.println("Dao saveFile start....");

        String url = MultipartFileUtils.saveFile(file, "/usr/local/erudition/"+saveLocalUrl , type);
        fileEntity.setUrl(url);

        System.out.println("Dao saveFile done....");


//        //提取关键字
//        String[] words = new String[100];
//        String thumbPath = null;
//        System.out.println("type : "+type);
//        if(type.equals("docx") || type.equals("doc") || type.equals("txt")){
//            WordFrequency wordFrequency = new WordFrequency();
//            words = wordFrequency.wordFreByWord(url,5);
//            System.out.println("提取关键字");
//        }else if(type.equals("wmv")){
//            thumbPath = new ProduceThumb().processVideoThumb(url);
//        }
//        else if(type.equals("png") || type.equals("jpg")){
//            try {
//                thumbPath = new ProduceThumb().processPictureThumb(url);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }



        fileEntity.setKeywords(categoryDao.getById(Integer.valueOf(cate1)).getCategoryName() +
                categoryDao.getById(Integer.valueOf(cate2)).getCategoryName() +
                category.getCategoryName() + file.getOriginalFilename() + "#"+keywords);

//        fileEntity.setThumb(thumbPath);


        save(fileEntity);


        System.out.println("Dao out ......");

        String name = url.substring(url.lastIndexOf("/")+1);
        System.out.println("name:----->"+name);
        return name;
    }

    public List<FilesEntity> getByTitle(String title) {
        String hql = "from FilesEntity as files where files.title=?";
        Query query = query(hql);
        query.setString(0, title);
        return query.list();
    }


    private String getFileTypeByFileName(String name) {

        int index = name.lastIndexOf('.');

        return name.substring(index + 1);

    }

    private String exchangeSize(long size){
        double initsize = size;
        String nowsize;
        int layer=1;
        while(initsize>=1024){
            initsize/=1024.0;
            layer++;
        }
        DecimalFormat df2 = new DecimalFormat("###.00");
        String initsize2 = df2.format(initsize);
        System.out.println("layer:"+layer);
        System.out.println("initsize2:"+initsize2);
        switch (layer){
            case 1:nowsize = initsize2+"B";break;
            case 2:nowsize = initsize2+"KB";break;
            case 3:nowsize = initsize2+"MB";break;
            case 4:nowsize = initsize2+"GB";break;
            case 5:nowsize = initsize2+"TB";break;
            case 6:nowsize = initsize2+"PB";break;
            default:nowsize = initsize2+"B";break;
        }
        System.out.println("nowsize: "+nowsize);
        return nowsize;
    }


    public void addLog(int userId , int fileId){
        LogEntity log = new LogEntity();
        log.setFileId(fileId);
        log.setUserId(userId);
        save(log);
    }


}
