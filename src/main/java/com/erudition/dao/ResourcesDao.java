package com.erudition.dao;

import com.erudition.bean.CategoryEntity;
import com.erudition.bean.FilesEntity;
import com.erudition.bean.LogEntity;
import com.erudition.bean.UserEntity;
import com.erudition.page.Page;
import com.erudition.page.PageHandler;
import com.erudition.util.MultipartFileUtils;
import com.erudition.util.ProduceThumb;
import com.erudition.util.TextRead;
import com.erudition.util.ansj.WordAnalyzer;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by tsj on 16-5-3.
 */
@Repository("resourcesDao")
public class ResourcesDao extends BaseDao {

    public String keyWords[] = new String[100];

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

        Map<Integer,Integer> quchong = new HashMap<>();

//        List<Integer> exitedFilesId = new ArrayList<>();

        for(FilesEntity file : files) {
            if(file != null){
                //filesExits.add(file);
                quchong.put(file.getId(),1);
            }
        }

        for(FilesEntity file : files){

            String relations = file.getRelations();
            System.out.println("relations: "+relations);
            if(relations != null){

                String [] relationsarr = relations.split(",");
                for(String re:relationsarr){

                    boolean flag = true;
                    if(re != null && !re.equals("")){
                        int re_id = Integer.valueOf(re);

                        if(!quchong.containsKey(re_id)){
                            relationFiles.add(getById(re_id));
                            quchong.put(re_id,1);
                        }

                    }

                }
            }

            if(relationFiles.size()>=6){
                break;
            }

        }
        if(relationFiles.size()==0){
            relationFiles.add(getById(142));
            relationFiles.add(getById(148));

            relationFiles.add(getById(157));

            relationFiles.add(getById(162));
            relationFiles.add(getById(170));
            System.out.println("111111111111123121111111111111");


        }
        System.out.println("大小大小大小:::::::"+relationFiles.size());
        for(FilesEntity f:relationFiles){
            System.out.println("文件！！"+f.getTitle());
        }
        return relationFiles;
    }


    public String saveFiles(String cate1, String cate2, String cate3, String keywords ,String originalName, MultipartFile file, UserEntity user){
        FilesEntity fileEntity = new FilesEntity();

        fileEntity.setTitle(originalName);
        fileEntity.setCreateTime(new Timestamp(System.currentTimeMillis()));
        fileEntity.setSize(exchangeSize(file.getSize()));
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


        //提取关键字
        String[] words = new String[100];
        String keyWordsTosave = "";

        if(type.equals("doc")){
            TextRead textRead = new TextRead();
            String content = textRead.getStringFromWord(url);
            WordAnalyzer wordAnalyzer = new WordAnalyzer();
            String keywordsFromAnalyzer = "";
            try {
                keywordsFromAnalyzer = wordAnalyzer.count(content,5);
            } catch (IOException e) {
                e.printStackTrace();
            }
            keyWordsTosave = keywordsFromAnalyzer+" "+keywords;

        }else{
            keyWordsTosave = keywords;
        }

        fileEntity.setKeywords(categoryDao.getById(Integer.valueOf(cate1)).getCategoryName() +
                categoryDao.getById(Integer.valueOf(cate2)).getCategoryName() +
                category.getCategoryName() + file.getOriginalFilename() + "#"+keyWordsTosave);


        keyWords = keyWordsTosave.split(" ");

        System.out.println("dao关键词:-------->"+keywords);



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

    public List<FilesEntity> getAllFiles(){
        String hql = "from FilesEntity as files";
        Query query = query(hql);
        return query.list();
    }

    public int getMaxId(){
        String hql = "select max(files.id) from FilesEntity as files";
        Query query = query(hql);
        int maxid = (int)query.uniqueResult();
        return maxid;
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

    public String[] getKeyWords(){
        return keyWords;
    }

}
