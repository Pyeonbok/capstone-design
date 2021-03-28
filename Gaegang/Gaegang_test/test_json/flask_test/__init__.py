from flask import Flask
from flask_restful import reqparse, Api, Resource
import pandas as pd
from sklearn.feature_extraction.text import TfidfVectorizer
from sklearn.metrics.pairwise import cosine_similarity
import json

# Flask 인스턴스 생성
app = Flask(__name__)
api = Api(app)

# 할일 정의
with open('data.json', 'r', encoding='utf-8') as f:
    json_data = json.load(f)

CLASSES = json.dumps(json_data, ensure_ascii=False)
jsob = json.loads(CLASSES)

# for i in range(1,10):
#     print(jsob[i].get("교과목명"))

line = len(jsob)

# print(jsonSubject.get("교과목명"))

# print(CLASSES)

# CLASSES = {
#     'class1': {'lecture': '아랍어1', 'professor': '교수명',
#                'classification': 'String', 'credit': 'String',
#                'teaching_method': 'String',
#                'week': 'String', 'time': 'String'},
#     'class2': {'lecture': '일본어1', 'professor': '교수명',
#                'classification': 'String', 'credit': 'String',
#                'teaching_method': 'String',
#                'week': 'String', 'time': 'String'},
#     'class3': {'lecture': '중국어1', 'professor': '교수명',
#                'classification': 'String', 'credit': 'String',
#                'teaching_method': 'String',
#                'week': 'String', 'time': 'String'}
# }

# def abort_if_todo_doesnt_exist(todo_id):
#     if todo_id not in CLASSES:
#         abort(404, message="Todo {} doesn't exist".format(todo_id))
#
#
parser = reqparse.RequestParser()
parser.add_argument('task')


# # 할일(Todo)
# # Get, Delete, Put 정의
# class Todo(Resource):
#     def get(self, todo_id):
#         abort_if_todo_doesnt_exist(todo_id)
#         return CLASSES[todo_id]
#
#     def delete(self, todo_id):
#         abort_if_todo_doesnt_exist(todo_id)
#         del CLASSES[todo_id]
#         return '', 204
#
#     def put(self, todo_id):
#         args = parser.parse_args()
#         task = {'task': args['task']}
#         CLASSES[todo_id] = task
#         return task, 201

# 할일 리스트(Todos)
# Get, POST 정의
class TodoList(Resource):

    def get(self, stt_text):
        global str3

        print(stt_text)
        df = pd.read_csv('test_class_final.csv', encoding='utf-8')

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
        print(class_Num)
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
            for i in range(1, line):
                if str2 == jsob[i].get("학수번호"):  # 비교해서 같을 경우
                    str3 = jsob[i].get("교과목명"), jsob[i].get("담당교수"), jsob[i].get("시간표")
                    # 전체정보출력가능
                    list.append(str3)
                    print(jsob[i].get("교과목명"), jsob[i].get("담당교수"), jsob[i].get("시간표"))

        l = dict(zip(range(1, len(str3) + 1), str3))  # 추출한 정보들을 json으로 변환.
        l = json.dumps(l, ensure_ascii=False)
        jsonObject2 = json.loads(l)

        return jsonObject2


##
## URL Router에 맵핑한다.(Rest URL정의)
##
##api.add_resource(TodoList, '/todos/')
api.add_resource(TodoList, '/stt/classes/<string:stt_text>')
# api.add_resource(Todo, '/todos/<string:todo_id>')

# 서버 실행
if __name__ == '__main__':
    app.run(debug=True)
