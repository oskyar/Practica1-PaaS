require 'sinatra'
require 'haml'

get '/' do
  erb :index
end

get '/' do
  haml '%div.title Hello World'
end
