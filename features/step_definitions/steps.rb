When('I login as {string} with password {string}') do |user, password|
  userData = {
    :username => user,
    :password => password
  }

  response = RestClient.post("#{BASE_URL}/login", userData.to_json)
  fail if response.code != 200

  @jwt = {} if @jwt.nil?
  @jwt[:user] = response.headers[:authorization]
end

When('{string} adds some data') do |user|
  userData = {
    :content => "Content for #{user}"
  }

  response = RestClient.post("#{BASE_URL}/data", userData.to_json, { 'Content-type' => 'application/json', :authorization => @jwt[:user] } )
  fail if response.code != 200
end

When('the data is returned for {string}') do |user|
  expectedContent = {
    "content" => "Content for #{user}"
  }

  response = RestClient.get("#{BASE_URL}/data", { :authorization => @jwt[:user] } )
  fail if response.code != 200

  data_returned = JSON.parse(response.body)
  expect(data_returned).to include expectedContent
end
