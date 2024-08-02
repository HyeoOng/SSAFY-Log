package com.ssafy.fit.model.dao;
import java.io.*;
import java.lang.reflect.Type;
import java.util.*;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.ssafy.fit.model.Video;

public class VideoDaoImpl implements VideoDao {
	Type videoListType = new TypeToken<ArrayList<Video>>() {}.getType();

	private List<Video> list;

	private static VideoDaoImpl instance;

	private VideoDaoImpl() {

		try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("data/video.json")));) {
			String str = null; // 한줄씩 읽어오기 위한 임시변수
			StringBuilder sb = new StringBuilder();

			while ((str = br.readLine()) != null) {
				sb.append(str);
			}

			Gson gson = new Gson();
			list = gson.fromJson(sb.toString(), videoListType);
		} catch (Exception e) {

		}
	}

	public static VideoDaoImpl getInstance() {
		if (instance == null) {
			instance = new VideoDaoImpl();
		}
		return instance;
	}

	@Override
	public List<Video> selectVideo() {
		return list;
	}

	@Override
	public Video selectVideoByNo(int no) {

		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getNo() == no) {
				return list.get(i);
			}
		}

		return null;
	}
}
