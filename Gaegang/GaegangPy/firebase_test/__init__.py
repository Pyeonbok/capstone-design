from flask import Flask
from flask_restful import reqparse, abort, Api, Resource
import pandas as pd
import numpy as np
from sklearn.feature_extraction.text import TfidfVectorizer
from sklearn.metrics.pairwise import cosine_similarity
import json
import firebase_admin
from firebase_admin import credentials
from firebase_admin import db
import sys

# Flask 인스턴스 생성
app = Flask(__name__)
api = Api(app)

cred = credentials.Certificate("capstone-da813-firebase-adminsdk-sfdae-c20c095062.json")
obj = firebase_admin.initialize_app(cred,{
    'databaseURL' : 'https://capstone-da813-default-rtdb.firebaseio.com/'})
dir = db.reference()

#리스너 함수
def listener(event):

    """
     #데이터출력
    print(event.event_type)  # can be 'put' or 'patch'
    print(event.path)  # relative to the reference, it seems
    print(event.data)
    """
    with open('data.json', 'r', encoding='utf-8') as f:
        json_data = json.load(f)

    CLASSES = json.dumps(json_data, ensure_ascii=False)
    jsob = json.loads(CLASSES)

    line = len(jsob)
    parser = reqparse.RequestParser()
    parser.add_argument('task')

    stt_text = str(event.data)
    # stt로 받은 텍스트로 추천 뽑기
    df = pd.read_csv('test_class_final_final.csv', encoding='utf-8')
    global str3
    # text = '예정용 교수님 교양 추천해줘'  # 안드로이드에서 받아올 텍스트
    df.loc[(df['class'] == '검색'), 'keyword'] = stt_text  # 검색을 받아온 텍스트로 함.
    # print(df)

    tfidf_vectorizer = TfidfVectorizer()  # TF-IDF 객체선언
    tfidf_matrix = tfidf_vectorizer.fit_transform(df['keyword'])

    cosine_matrix = cosine_similarity(tfidf_matrix, tfidf_matrix)

    class2id = {}
    for i, c in enumerate(df['class']): class2id[i] = c
    id2class = {}
    for i, c in class2id.items(): id2class[c] = i
    class_Num = {}
    for i, c in enumerate(df['class_num']): class_Num[i] = c

    idx = id2class['검색']
    # print(class2id)
    # print(id2class)
    # print(class_Num)
    sim_scores = [(i, c) for i, c in enumerate(cosine_matrix[idx]) if i != idx]  # 자기 자신을 제외한 영화들의 유사도 및 인덱스를 추출
    sim_scores = sorted(sim_scores, key=lambda x: x[1], reverse=True)  # 유사도가 높은 순서대로 정렬

    sim_scores = [(class_Num[i], score) for i, score in sim_scores[0:10]]
    # print(sim_scores)
    res = []
    for i in range(10):
        if float(sim_scores[i][1]) > 0:
            res.append(sim_scores[i][0])

    m = dict(zip(range(1, len(res) + 1), res))  # 상위10개과목 제이슨으로 변환
    m = json.dumps(m, ensure_ascii=False)
    jsonObject = json.loads(m)

    # print(m)
    list = []
    for i in range(1, 11):
        tmp = str(i)
        str2 = str(jsonObject.get(tmp))
        for i in range(0, line):
            if str2 == jsob[i].get("학수번호"):  # 비교해서 같을 경우
                str3 = jsob[i].get("교과목명"), jsob[i].get("담당교수"), jsob[i].get("학수번호"), \
                       jsob[i].get("대학(원)"), jsob[i].get("학과(부)"), jsob[i].get("이수구분"), \
                       jsob[i].get("학년"), jsob[i].get("학점"), jsob[i].get("수업방법관리"), jsob[i].get("시간표")
                # 전체정보출력가능

                list.append(str3)
    #print(list)
    #print(len(list))
    # 추출한 정보들을 json으로 변환.
    l = dict(zip(range(0, len(list) + 1), list))
    l = json.dumps(l, ensure_ascii=False, indent="\t")
    #print(l)
    jsonObject2 = json.loads(l)
    print(jsonObject2)
    dir = db.reference('Recommendedclass')
    dir.update(jsonObject2)

""" 
    mod = sys.modules[__name__]
    for idx in range(10):
        setattr(mod, 'rec_ref_{}'.format(idx),dir.child('recommended').child('recommended_{}'.format(idx)))

        getattr(mod, 'rec_ref_{}'.format(idx)).update({'lecture' : list[idx][0]})
        getattr(mod, 'rec_ref_{}'.format(idx)).update({'professor' : list[idx][1]})
        getattr(mod, 'rec_ref_{}'.format(idx)).update({'number':list[idx][2]})
        getattr(mod, 'rec_ref_{}'.format(idx)).update({'uni':list[idx][3]})
        getattr(mod, 'rec_ref_{}'.format(idx)).update({'major':list[idx][4]})
        getattr(mod, 'rec_ref_{}'.format(idx)).update({'classification':list[idx][5]})
        getattr(mod, 'rec_ref_{}'.format(idx)).update({'grade':list[idx][6]})
        getattr(mod, 'rec_ref_{}'.format(idx)).update({'credit':list[idx][7]})
        getattr(mod, 'rec_ref_{}'.format(idx)).update({'teaching_method':list[idx][8]})
        getattr(mod, 'rec_ref_{}'.format(idx)).update({'time':list[idx][9]})
"""

#리스너 실행
db.reference('sttText', app= obj).listen(listener)

# 서버 실행
if __name__ == '__main__':
    app.run(debug=True)