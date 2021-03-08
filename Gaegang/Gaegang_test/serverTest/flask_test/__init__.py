from flask import Flask
from flask_restful import reqparse, abort, Api, Resource

# Flask 인스턴스 생성
app = Flask(__name__)
api = Api(app)

# 할일 정의
CLASSES = {
    'class1': {'lecture': '아랍어1', 'professor': '교수명',
               'classification': 'String', 'credit': 'String',
               'teaching_method': 'String',
               'week': 'String', 'time': 'String'},
    'class2': {'lecture': '일본어1', 'professor': '교수명',
               'classification': 'String', 'credit': 'String',
               'teaching_method': 'String',
               'week': 'String', 'time': 'String'},
    'class3': {'lecture': '중국어1', 'professor': '교수명',
               'classification': 'String', 'credit': 'String',
               'teaching_method': 'String',
               'week': 'String', 'time': 'String'}
}


# 예외 처리
def abort_if_todo_doesnt_exist(todo_id):
    if todo_id not in CLASSES:
        abort(404, message="Todo {} doesn't exist".format(todo_id))


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
        print(stt_text)
        return CLASSES

    def post(self):
        args = parser.parse_args()
        todo_id = 'todo%d' % (len(CLASSES) + 1)
        CLASSES[todo_id] = {'task': args['task']}
        return CLASSES[todo_id], 201


##
## URL Router에 맵핑한다.(Rest URL정의)
##
##api.add_resource(TodoList, '/todos/')
api.add_resource(TodoList, '/stt/classes/<string:stt_text>')
#api.add_resource(Todo, '/todos/<string:todo_id>')

# 서버 실행
if __name__ == '__main__':
    app.run(debug=True)
