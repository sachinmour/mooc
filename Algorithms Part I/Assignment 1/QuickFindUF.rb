class QuickFindUF
  def initialize(n)
    @count = n
    @id = (0...n).to_a
  end
  
  def count
    return @count.to_s + " Components"
  end
  
  def connected?(a, b)
    return find(a) == find(b)
  end
  
  def find(a)
    return @id[a]
  end
  
  def union(a, b)
    id_a = @id[a]
    id_b = @id[b]
    
    return if id_a == id_b
    @id.map! { |e| (e == id_a) ? id_b : e }
    @count-=1
  end
  
  def self.main
    n = gets.chomp.to_i
    qf = QuickFindUF.new(n)
    while (a_and_b = gets and !a_and_b.empty?)
      a_and_b = a_and_b.chomp.split(/\s+/)
      a = a_and_b[0].to_i
      b = a_and_b[1].to_i
      next if qf.connected?(a, b)
      qf.union(a, b)
      puts "#{a} #{b}"
    end
    puts qf.count
  end
  
end

QuickFindUF.main